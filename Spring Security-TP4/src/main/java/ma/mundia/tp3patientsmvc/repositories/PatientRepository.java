package ma.mundia.tp3patientsmvc.repositories;

import ma.mundia.tp3patientsmvc.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

//fait connexion entre la bdd et notre repertoire
/*
Elle permet de chercher des patients dont le nom contient une chaîne de caractères donnée (kw).
Elle utilise la pagination grâce à l'argument Pageable,
 ce qui signifie que les résultats peuvent être divisés en pages pour faciliter leur affichage
 (par exemple, 10 patients par page).
 */
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findByNameContains(String kw, Pageable pageable);
}
