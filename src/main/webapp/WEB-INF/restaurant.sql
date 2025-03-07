CREATE DATABASE RestaurantDB;
USE RestaurantDB;

-- Table Client
CREATE TABLE Client (
    client_id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    telephone VARCHAR(100) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Table Livreur
CREATE TABLE Livreur (
    livreur_id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    telephone VARCHAR(100) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Table Admin
CREATE TABLE Admin (
    admin_id INT AUTO_INCREMENT PRIMARY KEY,
	nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Table Serveur
CREATE TABLE Serveur (
    serveur_id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Table Categorie
CREATE TABLE Categorie (
    categorie_id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL
);

-- Table Menu
CREATE TABLE Menu (
    menu_id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    description TEXT,
    prix DECIMAL(10, 2) NOT NULL,
    categorie_id INT,
    FOREIGN KEY (categorie_id) REFERENCES Categorie(categorie_id)
);

-- Table Commande
CREATE TABLE Commande (
    commande_id INT AUTO_INCREMENT PRIMARY KEY,
    client_id INT,
    livreur_id INT,
    total_prix DECIMAL(10, 2),
    status ENUM('en attente', 'en preparation', 'prete', 'en livraison', 'livree'),
    adresse_client VARCHAR(100) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (client_id) REFERENCES Client(client_id),
    FOREIGN KEY (livreur_id) REFERENCES Livreur(livreur_id)
);

-- Table DetailsCommande
CREATE TABLE DetailsCommande (
    details_id INT AUTO_INCREMENT PRIMARY KEY,
    commande_id INT,
    menu_id INT,
    menu_nom VARCHAR(255) NOT NULL,
    quantite INT NOT NULL,
    prix_unitaire DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (commande_id) REFERENCES Commande(commande_id),
    FOREIGN KEY (menu_id) REFERENCES Menu(menu_id)
);