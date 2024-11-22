package ma.mundia.tp3patientsmvc;

import ma.mundia.tp3patientsmvc.entities.Patient;
import ma.mundia.tp3patientsmvc.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class Tp3PatientsMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(Tp3PatientsMvcApplication.class, args);
    }

    //@Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository) {
        return args -> {
            //insérer des patients
            patientRepository.save(new Patient(null, "Zineb", new Date(),false,170));
            patientRepository.save(new Patient(null, "Ahmed", new Date(), true, 300));
            patientRepository.save(new Patient(null, "Layla", new Date(), false, 200));
            patientRepository.save(new Patient(null, "Mourad", new Date(), true, 500));
            patientRepository.save(new Patient(null, "Sofia", new Date(), false, 320));
            patientRepository.save(new Patient(null, "Omar", new Date(), true, 122));

            patientRepository.findAll().forEach(p->{
                System.out.println(p.getName());
            });

        };
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
