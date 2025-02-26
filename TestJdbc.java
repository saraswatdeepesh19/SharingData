import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    // System cache (stores all devices)
    private final ConcurrentHashMap<String, List<DeviceDTO>> deviceCache = new ConcurrentHashMap<>();

    public DeviceDTO getDeviceById(String deviceId) {
        // 1️⃣ Check if all devices are cached
        List<DeviceDTO> cachedDevices = deviceCache.get("allDevices");

        if (cachedDevices != null) {
            // Search for the requested device in cache
            return cachedDevices.stream()
                    .filter(device -> deviceId.equals(device.getDeviceId()))
                    .findFirst()
                    .orElseGet(() -> refreshCacheAndFind(deviceId));
        }

        // 2️⃣ If cache is empty, refresh it and search again
        return refreshCacheAndFind(deviceId);
    }

    private DeviceDTO refreshCacheAndFind(String deviceId) {
        // Fetch all devices from DB
        List<Device> dbDevices = deviceRepository.findAll();
        if (!dbDevices.isEmpty()) {
            List<DeviceDTO> deviceList = dbDevices.stream()
                    .map(device -> new DeviceDTO(device.getUdid(), device.getDeviceName(), device.getPlatformVersion()))
                    .collect(Collectors.toList());

            deviceCache.put("allDevices", deviceList); // Store in cache

            return deviceList.stream()
                    .filter(device -> deviceId.equals(device.getDeviceId()))
                    .findFirst()
                    .orElse(new DeviceDTO(deviceId, null, null));
        }

        // 3️⃣ If DB is empty, return null values
        deviceCache.clear();
        return new DeviceDTO(deviceId, null, null);
    }
}
