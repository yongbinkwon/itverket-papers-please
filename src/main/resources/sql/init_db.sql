CREATE TABLE ImmigrationApplication
(
    Id             INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    ProcessId      UUID         NOT NULL UNIQUE,
    ExpectedResult VARCHAR(255) NOT NULL,
    Face           UUID         NOT NULL,
    ImmigrationDay INT NOT NULL
);

CREATE TABLE Visa
(
    Id                           INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    Employer                     VARCHAR(255) NOT NULL,
    EmployeeId                   UUID         NOT NULL,
    Image                        UUID         NOT NULL,
    Fk__Visa__ImmigrationApplication  INT,

    FOREIGN KEY (Fk__Visa__ImmigrationApplication ) REFERENCES ImmigrationApplication (Id) ON DELETE CASCADE
);

CREATE TABLE Passport
(
    Id                               INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    Nationality                      VARCHAR(255) NOT NULL,
    SocialSecurityNumber             UUID NOT NULL,
    Image                            UUID NOT NULL,
    Fk__Passport__ImmigrationApplication  INT,

    FOREIGN KEY (Fk__Passport__ImmigrationApplication ) REFERENCES ImmigrationApplication (Id) ON DELETE CASCADE
);

CREATE TABLE NationalRegistry
(
    Id                               INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    SocialSecurityNumber             UUID NOT NULL
);

CREATE TABLE ForeignerRegistry
(
    Id                               INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    SocialSecurityNumber             UUID NOT NULL,
    Nationality VARCHAR(255) NOT NULL
);

CREATE TABLE CompanyRegistry
(
    Id                               INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    EmployeeId             UUID NOT NULL,
    Company VARCHAR(255) NOT NULL
);