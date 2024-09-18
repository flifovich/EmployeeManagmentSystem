CREATE DATABASE sistemoop;
USE sistemoop;

CREATE TABLE zaposleni (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ime VARCHAR(100) NOT NULL,
    prezime VARCHAR(100) NOT NULL,
    adresa VARCHAR(255),
    email VARCHAR(100) NOT NULL,
    datum_rodjenja DATE,
    broj_telefona VARCHAR(20),
    pozicija VARCHAR(100),
    plata DOUBLE
);

CREATE TABLE prisustvo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_zaposlenog INT NOT NULL,
    ime VARCHAR(100) NOT NULL,
    prezime VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    smena VARCHAR(50),
    status VARCHAR(50),
    datum TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_zaposlenog) REFERENCES zaposleni(id) ON DELETE CASCADE
);

CREATE TABLE odsustvo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_zaposlenog INT NOT NULL,
    ime VARCHAR(100) NOT NULL,
    prezime VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    datum_pocetka DATE NOT NULL,
    datum_kraja DATE NOT NULL,
    razlog TEXT,
    FOREIGN KEY (id_zaposlenog) REFERENCES zaposleni(id) ON DELETE CASCADE
);
CREATE TABLE logindata (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50) NOT NULL
);

INSERT INTO logindata (username, password)
VALUES ('admin', 'admin');

Za login sam koristio admin/admin, za konekciju na BB - "Root", ""; (nema passworda)
