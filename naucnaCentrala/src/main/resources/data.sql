insert into Casopis (id, naziv, tip_casopisa) values (1, 'Casopis1', 0);
insert into Casopis (id, naziv, tip_casopisa) values (2, 'Casopis2', 1);
insert into Casopis (id, naziv, tip_casopisa) values (3, 'Casopis3', 1);

insert into Autor (ime, prezime, drzava, grad, email, username, password) values ("Pera", "Peric", "Srbija", "Novi Sad", "uvrnuto@gmail.com", "pera", "pass");
insert into Autor (ime, prezime, drzava, grad, email, username, password) values ("Mika", "Mikic", "Srbija", "Novi Sad", "vukboskovic3@gmail.com", "mika", "pass");
insert into Autor (ime, prezime, drzava, grad, email, username, password) values ("Zika", "Zikic", "Srbija", "Novi Sad", "stefanr.ceranic@gmail.com", "zika", "pass");

insert into Urednik (ime, prezime, drzava, grad, email, naucna_oblast, tip_urednika, titula, casopis_id) values ("Urednik1", "Urednikic1", "Srbija", "Novi Sad", "uvrnuto@gmail.com", 0, 0, "doktor", 1);
insert into Urednik (ime, prezime, drzava, grad, email, naucna_oblast, tip_urednika, titula, casopis_id) values ("Urednik2", "Urednikic2", "Srbija", "Novi Sad", "vukboskovic3@gmail.com", 1, 1, "doktor", 2);
insert into Urednik (ime, prezime, drzava, grad, email, naucna_oblast, tip_urednika, titula, casopis_id) values ("Urednik3", "Urednikic3", "Srbija", "Novi Sad", "stefanr.ceranic@gmail.com", 2, 1, "doktor", 3);


insert into pretplatnici_casopisa (casopis_id, autor_id) values (1, 1);
insert into pretplatnici_casopisa (casopis_id, autor_id) values (1, 2);
insert into pretplatnici_casopisa (casopis_id, autor_id) values (1, 3);

insert into pretplatnici_casopisa (casopis_id, autor_id) values (2, 1);
insert into pretplatnici_casopisa (casopis_id, autor_id) values (2, 2);
insert into pretplatnici_casopisa (casopis_id, autor_id) values (2, 3);

insert into pretplatnici_casopisa (casopis_id, autor_id) values (3, 1);
insert into pretplatnici_casopisa (casopis_id, autor_id) values (3, 2);
insert into pretplatnici_casopisa (casopis_id, autor_id) values (3, 3);