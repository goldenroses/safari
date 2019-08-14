package co.nyenjes.safari.index

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class IndexController {
//    @Value("${spring.application.name}")
    val appName: String? = null

    @GetMapping("/")
    fun  homePage(model: Model): String {
        model.addAttribute("appName", "Safari");
        return "home"
    }

    @GetMapping("/places")
    fun  viewPlaces(model: Model): String {
        model.addAttribute("plces", "Safari");
        return "home"
    }
}
