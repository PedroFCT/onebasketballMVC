package br.com.pdrprojects.onebasketball.partida;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/partida")
public class partidaController {

    @Autowired
    PartidaService service;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("partida", service.findAll());
        return "partida/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        if (service.delete(id)) {
            redirect.addFlashAttribute("success", "Partida apagada com sucesso");
        } else {
            redirect.addFlashAttribute("error", "Partida não foi encontrada");
        }
        return "redirect:/partida";
    }
}
