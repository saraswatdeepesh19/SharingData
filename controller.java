@RestController
@RequestMapping("/devices")
public class HmlIosDevicesController {

    @Autowired
    private HmlIosDevicesService service;

    // Create a new device
    @PostMapping("/")
    public HmlIosDevices create(@RequestBody HmlIosDevices device) {
        return service.create(device);
    }

    // Get a device by udid
    @GetMapping("/{udid}")
    public HmlIosDevices read(@PathVariable String udid) {
        return service.read(udid);
    }

    // Update a device by udid
    @PutMapping("/{udid}")
    public HmlIosDevices update(@PathVariable String udid, @RequestParam(name = "psid", required = false) String psid) {
        return service.update(udid, psid);
    }

    // Delete a device by udid
    @DeleteMapping("/{udid}")
    public void delete(@PathVariable String udid) {
        service.delete(udid);
    }

    // Get a list of all devices
    @GetMapping("/")
    public List<HmlIosDevices> list() {
        return service.list();
    }

    // Handle exceptions
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
