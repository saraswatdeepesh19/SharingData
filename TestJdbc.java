import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    private final String externalApiUrl = "https://external-api.com/devices/"; // Replace with actual API

    // System cache (ConcurrentHashMap)
    private final ConcurrentHashMap<String, DeviceDTO> deviceCache = new ConcurrentHashMap<>();

    public DeviceDTO getDevice(String deviceId) {
        // 1️⃣ Check cache first
        if (deviceCache.containsKey(deviceId)) {
            return deviceCache.get(deviceId);
        }

        // 2️⃣ Fetch from database
        Optional<Device> dbDevice = deviceRepository.findById(deviceId);
        if (dbDevice.isPresent()) {
            DeviceDTO deviceDTO = new DeviceDTO(dbDevice.get().getDeviceName(), dbDevice.get().getPlatformVersion());
            deviceCache.put(deviceId, deviceDTO);
            return deviceDTO;
        }

        // 3️⃣ Fetch from External API
        RestTemplate restTemplate = new RestTemplate();
        try {
            DeviceDTO apiDevice = restTemplate.getForObject(externalApiUrl + deviceId, DeviceDTO.class);
            if (apiDevice != null) {
                deviceCache.put(deviceId, apiDevice);
                return apiDevice;
            }
        } catch (Exception e) {
            System.out.println("External API call failed: " + e.getMessage());
        }

        // 4️⃣ If not found, clear cache and return null values
        deviceCache.clear();
        return new DeviceDTO(null, null);
    }
}
