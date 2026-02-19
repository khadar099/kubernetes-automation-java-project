@GetMapping("/")
public String welcome() {

    String unused = "DevOps";  // Code smell (unused variable)

    if (true == true) {        // Code smell (always true condition)
        System.out.println("Welcome page accessed");
    }

    return "welcome";
}
