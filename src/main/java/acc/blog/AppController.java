package acc.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {
	
	@RequestMapping("/")
	public String def() {
		return home();
	}
	
	@RequestMapping("/home")
	public String home() {
		return "home";
	}

}
