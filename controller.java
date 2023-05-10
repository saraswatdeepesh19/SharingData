@RestController
@RequestMapping("/devices")
public class HmlIosDevicesController {

    @Autowired
    private HmlIosDevicesService service;

    @PostMapping("/")
    public HmlIosDevices create(@RequestBody HmlIosDevices device) {
        return service.create(device);
    }

    @GetMapping("/{udid}")
    public HmlIosDevices read(@PathVariable String udid) {
        return service.read(udid);
    }

    @PutMapping("/{udid}")
    public HmlIosDevices update(@PathVariable String udid, @RequestParam(name = "psid", required = false) String psid) {
        return service.update(udid, psid);
    }

    @DeleteMapping("/{udid}")
    public void delete(@PathVariable String udid) {
        service.delete(udid);
    }

    @GetMapping("/")
    public List<HmlIosDevices> list() {
        return service.list();
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
