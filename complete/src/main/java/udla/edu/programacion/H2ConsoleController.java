package udla.edu.programacion;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class H2ConsoleController {
    @GetMapping("/admin/h2console")
    public String h2Console() {
        return "redirect:/h2-console";
    }
}
