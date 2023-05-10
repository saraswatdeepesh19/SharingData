@Service
public class HmlIosDevicesService {

    @Autowired
    private HmlIosDevicesRepository repository;

    // Create a new device
    public HmlIosDevices create(HmlIosDevices device) {
        return repository.save(device);
    }

    // Get a device by udid
    public HmlIosDevices read(String udid) {
        return repository.findById(udid).orElseThrow(() -> new RuntimeException("Device with udid " + udid + " not found"));
    }

    // Update a device by udid
    public HmlIosDevices update(String udid, String psid) {
        // Find the device by udid
        HmlIosDevices device = repository.findById(udid).orElseThrow(() -> new RuntimeException("Device with udid " + udid + " not found"));

        // If the device already has a psid, set it to null and mark status as online
        if (device.getPsid() != null) {
            device.setPsid(null);
            device.setStatus("online");
        } else {
            // If the device doesn't have a psid, mark status as offline
            device.setStatus("offline");
        }

        // If a psid is provided, update the device's psid
        if (psid != null) {
            device.setPsid(psid);
        }

        // Save the updated device to the database
        return repository.save(device);
    }

    // Delete a device by udid
    public void delete(String udid) {
        repository.deleteById(udid);
    }

    // Get a list of all devices
    public List<HmlIosDevices> list() {
        return repository.findAll();
    }
}
