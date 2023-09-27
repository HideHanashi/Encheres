CREATE TABLE UTILISATEUR (

    no_utilisateur   INTEGER IDENTITY(1,1) NOT NULL,

    pseudo           VARCHAR(30) NOT NULL constraint uq_pseudo_user unique,

    nom              VARCHAR(30) NOT NULL,

    prenom           VARCHAR(30) NOT NULL,

    email            VARCHAR(300) NOT NULL constraint uq_email_user unique,

    telephone        VARCHAR(30) NOT NULL constraint uq_phone_user unique,

    rue              VARCHAR(50) NOT NULL,

    code_postal      CHAR(5) NOT NULL,

    ville            VARCHAR(50) NOT NULL,

    mot_de_passe     VARCHAR(300) NOT NULL,

    credit           INTEGER NOT NULL,

    administrateur   bit NOT NULL

);

 

ALTER TABLE UTILISATEUR ADD constraint utilisateur_pk PRIMARY KEY (no_utilisateur)

 

 

CREATE TABLE ENCHERES (

    no_utilisateur   INTEGER NOT NULL,

    no_article       INTEGER NOT NULL,

    date_encheres     datetime NOT NULL,

	montant_encheres  INTEGER NOT NULL

)

 

ALTER TABLE ENCHERES ADD constraint encheres_pk PRIMARY KEY (no_utilisateur, no_article)

 

 

CREATE TABLE ARTICLE_VENDU (

    no_article                    INTEGER IDENTITY(1,1) NOT NULL,

    nom_article                   VARCHAR(30) NOT NULL,

    [description]                 VARCHAR(300) NOT NULL,

	date_debut_encheres           datetime NOT NULL,

    date_fin_encheres              datetime NOT NULL,

    prix_initial                  INTEGER NOT NULL,

    prix_vente                    INTEGER NOT NULL,

	etat_vente					  VARCHAR(50) NOT NULL,

    no_utilisateur                INTEGER NOT NULL,

    no_categorie                  INTEGER NOT NULL

)

 

ALTER TABLE ARTICLE_VENDU ADD constraint article_vendu_pk PRIMARY KEY (no_article)

 

 

CREATE TABLE CATEGORIE (

    no_categorie   INTEGER IDENTITY(1,1) NOT NULL,

    libelle        VARCHAR(30) NOT NULL

)

 

ALTER TABLE CATEGORIE ADD constraint categorie_pk PRIMARY KEY (no_categorie)

 

 

CREATE TABLE RETRAIT (

	no_article         INTEGER NOT NULL,

    rue              VARCHAR(50) NOT NULL,

    code_postal      CHAR(5) NOT NULL,

    ville            VARCHAR(50) NOT NULL

)

 

ALTER TABLE RETRAIT ADD constraint retrait_pk PRIMARY KEY  (no_article)

 

ALTER TABLE ARTICLE_VENDU

    ADD CONSTRAINT enchere_utilisateur_fk FOREIGN KEY ( no_utilisateur ) REFERENCES UTILISATEUR ( no_utilisateur )

ON DELETE NO ACTION 

    ON UPDATE no action

 

ALTER TABLE ENCHERES

    ADD CONSTRAINT encheres_article_vendu_fk FOREIGN KEY ( no_article )

        REFERENCES ARTICLE_VENDU ( no_article )

ON DELETE NO ACTION 

    ON UPDATE no action

 

ALTER TABLE RETRAIT

    ADD CONSTRAINT retrait_article_vendu_fk FOREIGN KEY ( no_article )

        REFERENCES ARTICLE_VENDU ( no_article )

ON DELETE NO ACTION 

    ON UPDATE no action

 

ALTER TABLE ARTICLE_VENDU

    ADD CONSTRAINT article_vendu_categorie_fk FOREIGN KEY ( no_categorie )

        REFERENCES categorie ( no_categorie )

ON DELETE NO ACTION 

    ON UPDATE no action

 

ALTER TABLE ARTICLE_VENDU

    ADD CONSTRAINT vente_utilisateur_fk FOREIGN KEY ( no_utilisateur )

        REFERENCES utilisateur ( no_utilisateur )

ON DELETE NO ACTION 

    ON UPDATE no action

 

 

CREATE TABLE FORGET_PASSWORD(

	id bigint IDENTITY(1,1) NOT NULL,

	code varchar(10) NOT NULL,

	no_utilisateur integer NOT NULL,

	date_created datetime NULL,

)

ALTER TABLE FORGET_PASSWORD add CONSTRAINT pk_forget_password PRIMARY KEY (id, no_utilisateur)

 

ALTER TABLE FORGET_PASSWORD ADD CONSTRAINT fk_forget_password FOREIGN KEY(no_utilisateur)

REFERENCES UTILISATEUR (no_utilisateur)

 

ALTER TABLE FORGET_PASSWORD

ADD CONSTRAINT df_date 

DEFAULT GETDATE()

FOR date_created