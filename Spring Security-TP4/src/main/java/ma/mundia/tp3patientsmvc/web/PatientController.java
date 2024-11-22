package ma.mundia.tp3patientsmvc.web;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;  // Ajoute cette ligne
import lombok.AllArgsConstructor;
import ma.mundia.tp3patientsmvc.entities.Patient;
import ma.mundia.tp3patientsmvc.repositories.PatientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.Binding;
import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {

    private PatientRepository patientRepository;
/*
    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
 */
    @GetMapping(path = "/user/index")
    public String patients(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "6") int size,
                           @RequestParam(name = "keyword", defaultValue = "")String keyword
    ) {
        Page<Patient> pagePatients = patientRepository.findByNameContains(keyword, PageRequest.of(page, size));
        model.addAttribute("listpatients", pagePatients.getContent());
        model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "patients";
    }
    @GetMapping("/admin/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(Long id, String keyword, int page) {
        patientRepository.deleteById(id);
        return "redirect:/user/index?page=" + page+"&keyword=" + keyword;
    }
    /*
    @GetMapping("/")
    public String home(){
        //patientRepository.deleteById(id);
        return "redirect:/index";
    }
*/
    @GetMapping("/patients") //mapper les requêtes HTTP de type GET à une méthode d'un contrôleur
    @ResponseBody //le contenu de la réponse retournée au client par un serveur.
    public List<Patient> listPatients() {
        return patientRepository.findAll();
    }

    @GetMapping("/admin/formPatients")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String formPatients(Model model) {
        model.addAttribute("patient", new Patient());
        return "formPatients";
    }

    @PostMapping(path = "/admin/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String save(Model model, @Valid Patient patient, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String keyword) {
        if(bindingResult.hasErrors()) {
            return "formPatients";
        }
        patientRepository.save(patient);
        return "redirect:/index?page"+page+"&keyword="+keyword;
    }

    @GetMapping("/admin/editPatient")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editPatient(Model model, Long id, String keyword, int page) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if(patient == null) {
            throw new RuntimeException("Patient not found");
        }
        model.addAttribute("patient", patient);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        return "editPatient";
    }

    @GetMapping("/")
    public String home(){
        return "redirect:/user/index";
    }

}
