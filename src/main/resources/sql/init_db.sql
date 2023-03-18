CREATE TABLE ImmigrationProcess
(
    Id             INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    ProcessId      UUID         NOT NULL UNIQUE,
    ImmigrationDay INT          NOT NULL,
    ExpectedResult VARCHAR(255) NOT NULL,
    Face           UUID         NOT NULL
);

CREATE TABLE Visa
(
    Id                           INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    EmployerName                 VARCHAR(255) NOT NULL,
    EmployeeName                 VARCHAR(255) NOT NULL,
    EmployeeId                   UUID         NOT NULL,
    Image                        UUID         NOT NULL,
    Fk__Visa__ImmigrationProcess INT,

    FOREIGN KEY (Fk__Visa__ImmigrationProcess) REFERENCES ImmigrationProcess (Id) ON DELETE CASCADE
);

CREATE TABLE Passport
(
    Id                               INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    Nationality                      VARCHAR(255),
    SocialSecurityNumber             VARCHAR(255),
    Image                            UUID NOT NULL,
    Fk__Passport__ImmigrationProcess INT,

    FOREIGN KEY (Fk__Passport__ImmigrationProcess) REFERENCES ImmigrationProcess (Id) ON DELETE CASCADE
);

CREATE TABLE CompanyRegistry
(
    Id           INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    EmployeeName VARCHAR(255) NOT NULL,
    EmployeeId   UUID         NOT NULL,
    Image        UUID         NOT NULL,
)