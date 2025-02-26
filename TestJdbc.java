import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    private final String externalApiUrl = "https://external-api.com/devices"; // Replace with actual API

    // System cache (stores all devices)
    private final ConcurrentHashMap<String, List<DeviceDTO>> deviceCache = new ConcurrentHashMap<>();

    public List<DeviceDTO> getAllDevices() {
        // 1️⃣ Check cache first
        if (deviceCache.containsKey("allDevices")) {
            return deviceCache.get("allDevices");
        }

        // 2️⃣ Fetch from database
        List<Device> dbDevices = deviceRepository.findAll();
        if (!dbDevices.isEmpty()) {
            List<DeviceDTO> deviceList = dbDevices.stream()
                    .map(device -> new DeviceDTO(device.getDeviceName(), device.getPlatformVersion()))
                    .collect(Collectors.toList());
            
            deviceCache.put("allDevices", deviceList); // Store in cache
            return deviceList;
        }

        // 3️⃣ Fetch from External API
        RestTemplate restTemplate = new RestTemplate();
        try {
            DeviceDTO[] apiDevices = restTemplate.getForObject(externalApiUrl, DeviceDTO[].class);
            if (apiDevices != null && apiDevices.length > 0) {
                List<DeviceDTO> deviceList = List.of(apiDevices);
                deviceCache.put("allDevices", deviceList); // Store in cache
                return deviceList;
            }
        } catch (Exception e) {
            System.out.println("External API call failed: " + e.getMessage());
        }

        // 4️⃣ If not found, clear cache and return an empty list
        deviceCache.clear();
        return List.of();
    }
}
