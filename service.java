@Service
public class HmlIosDevicesService {

    @Autowired
    private HmlIosDevicesRepository repository;

    public HmlIosDevices create(HmlIosDevices device) {
        return repository.save(device);
    }

    public HmlIosDevices read(String udid) {
        return repository.findById(udid).orElseThrow(() -> new RuntimeException("Device with udid " + udid + " not found"));
    }

    public HmlIosDevices update(String udid, String psid) {
        HmlIosDevices device = repository.findById(udid).orElseThrow(() -> new RuntimeException("Device with udid " + udid + " not found"));

        if (device.getPsid() != null) {
            device.setPsid(null);
            device.setStatus("online");
        } else {
            device.setStatus("offline");
        }

        if (psid != null) {
            device.setPsid(psid);
        }

        return repository.save(device);
    }

    public void delete(String udid) {
        repository.deleteById(udid);
    }

    public List<HmlIosDevices> list() {
        return repository.findAll();
    }
}
