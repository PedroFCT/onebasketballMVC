package br.com.pdrprojects.onebasketball.partida;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/partida")
public class partidaController {

    @Autowired
    PartidaService service;

    @Autowired
    MessageSource message;

    @GetMapping
    public String index(Model model, @AuthenticationPrincipal OAuth2User user) {
        model.addAttribute("username", user.getAttribute("username"));
        model.addAttribute("partida", service.findAll());
        return "partida/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        if (service.delete(id)) {
            redirect.addFlashAttribute("success", "partida.delete.sucess");
        } else {
            redirect.addFlashAttribute("error", "partida.notfound");
        }
        return "redirect:/partida";
    }

     @PostMapping
    public String create(@Valid Partida partida, BindingResult result, RedirectAttributes redirect){
        if (result.hasErrors()) return "partida/form";
        service.save(partida);
        redirect.addFlashAttribute("success", getMessage("partida.create.success"));
        return "redirect:/partida";
    }

    private String getMessage(String code){
        return message.getMessage(code, null, LocaleContextHolder.getLocale());
    }

}
