<h1>Gestion des Patients - Spring Security</h1>

<h3>Projet réalisé par Zineb Feth-Eddine</h3>

---

<h2>Introduction</h2>
<p>
Ce projet consiste à développer une application web sécurisée pour la gestion des patients, basée sur <strong>Spring Security</strong>, <strong>Spring MVC</strong>, et <strong>Thymeleaf</strong>. Le projet utilise également <strong>Spring Data JPA</strong> pour l'interaction avec la base de données.
</p>
<ul>
    <li>Authentification des utilisateurs (In-Memory, JDBC, ou UserDetailsService).</li>
    <li>Gestion des rôles pour l'autorisation (ADMIN, USER).</li>
    <li>Affichage sécurisé des données avec pagination et recherche.</li>
    <li>Ajout, modification, et suppression des patients.</li>
    <li>Affichage d'une page "Non autorisé" lorsque l'accès est refusé.</li>
</ul>
<p>
La base de données utilisée est <strong>MySQL</strong>, avec création automatique des tables si elles n'existent pas.
</p>

---

<h2>Structure du Projet</h2>
<p>Le projet suit une architecture bien structurée respectant le paradigme <strong>MVC (Modèle-Vue-Contrôleur)</strong>.</p>
<img src="/screens/structure.png" alt="Structure du Projet" />

<h3>1. Package <code>entities</code></h3>
<ul>
    <li>Contient la classe <strong>Patient</strong>, représentant le modèle de données de l'application.</li>
    <li><strong>Attributs</strong> :
        <ul>
            <li><code>id</code> : Identifiant unique.</li>
            <li><code>nom</code> : Nom du patient.</li>
            <li><code>dateNaissance</code> : Date de naissance.</li>
            <li><code>malade</code> : Statut indiquant si le patient est malade ou non.</li>
        </ul>
    </li>
</ul>

<h3>2. Package <code>repositories</code></h3>
<ul>
    <li>Contient l'interface <strong>PatientRepository</strong>, héritant de <code>JpaRepository</code>.</li>
    <li>Fournit les opérations CRUD automatiques pour les patients.</li>
</ul>

<h3>3. Package <code>security</code></h3>
<ul>
    <li>Contient la classe <strong>SecurityConfig</strong>, qui configure :
        <ul>
            <li>Les stratégies d'authentification (<code>InMemoryAuthentication</code>, <code>JdbcAuthentication</code>, etc.).</li>
            <li>Les rôles et les droits d'accès pour chaque fonctionnalité.</li>
        </ul>
    </li>
</ul>

<h3>4. Package <code>web</code></h3>
<ul>
    <li>Contient les contrôleurs pour la logique métier et les interactions utilisateur :</li>
    <ul>
        <li><code>PatientController</code> : Gestion des opérations CRUD pour les patients.</li>
        <li><code>SecurityController</code> : Gestion des pages de connexion et des accès non autorisés.</li>
    </ul>
</ul>

<h3>5. Dossier <code>resources/templates</code></h3>
<ul>
    <li>Contient les fichiers <strong>Thymeleaf</strong> pour l'interface utilisateur :</li>
    <ul>
        <li><code>login.html</code> : Page de connexion.</li>
        <li><code>patients.html</code> : Liste des patients avec recherche et pagination.</li>
        <li><code>formPatients.html</code> : Formulaire pour ajouter/modifier un patient.</li>
        <li><code>notAuthorized.html</code> : Page affichée en cas d'accès non autorisé.</li>
    </ul>
</ul>

---

<h2>Configuration</h2>

<h3>1. Base de données</h3>
<p>Assurez-vous que MySQL est installé et configuré. Les détails de connexion sont définis dans <code>application.properties</code> :</p>

<pre>
server.port=8083
spring.datasource.url=jdbc:mysql://localhost:3306/PAT_DB?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MariaDBDialect
</pre>

<h3>2. Lancer l'application</h3>
<ol>
    <li>Clonez le repository et naviguez dans le dossier du projet :</li>
    <pre>
    git clone [URL_DU_REPOSITORY]
    cd [NOM_DU_PROJET]
    </pre>
    <li>Exécutez le projet avec Maven :</li>
    <pre>
    mvn spring-boot:run
    </pre>
    <li>Accédez à l'application via <a href="http://localhost:8083">http://localhost:8083</a>.</li>
</ol>

---

<h2>Captures d'écran</h2>

<h3>1. Page de connexion</h3>
<img src="Spring Security-TP4/Screens/a.png" alt="Page de connexion" />

<h3>2. Liste des patients</h3>
<img src="Spring Security-TP4/Screens/d.png" alt="Liste des patients" />

<h3>3. Page non autorisée</h3>
<img src="Spring Security-TP4/Screens/e.png" alt="Page non autorisée" />

---

<h2>Description des Fonctionnalités</h2>

<h3>1. Authentification</h3>
<p>Les utilisateurs peuvent se connecter en fonction de la méthode configurée :</p>
<ul>
    <li><code>InMemoryAuthentication</code> : Utilisateurs définis dans le code.</li>
    <li><code>JdbcAuthentication</code> : Utilisateurs stockés dans la base de données.</li>
    <li><code>UserDetailsService</code> : Implémentation personnalisée pour charger les utilisateurs.</li>
</ul>

<h3>2. Liste des patients</h3>
<p>Accessible uniquement aux utilisateurs autorisés. Affiche une liste paginée avec une barre de recherche pour filtrer les patients.</p>

<h3>3. Ajout et modification de patients</h3>
<p>Permet d'ajouter un nouveau patient ou de modifier les informations existantes uniquement aux utilisateurs ayant le rôle.</p>

<h3>4. Suppression de patients</h3>
<p>Accessible uniquement aux utilisateurs ayant le rôle <code>ADMIN</code>.</p>

<h3>5. Gestion des accès</h3>
<p>Affichage d'une page "Non autorisé" (<code>notAuthorized.html</code>) si un utilisateur tente d'exécuter une action sans avoir les droits nécessaires.</p>

---

<h2>Conclusion</h2>
<p>
Ce projet met en œuvre les concepts essentiels de <strong>Spring Security</strong> et peut servir de base pour développer des applications web sécurisées avec des fonctionnalités avancées.
</p>
