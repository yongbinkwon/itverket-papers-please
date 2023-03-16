CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE ImmigrationProcess
(
    Id             INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    ProcessId      UUID         NOT NULL UNIQUE DEFAULT uuid_generate_v4(),
    ImmigrationDay INT          NOT NULL,
    ExpectedResult VARCHAR(255) NOT NULL
);

CREATE TABLE Visa
(
    Id           INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    EmployerName VARCHAR(255),
    EmployeeName VARCHAR(255),
    EmployeeId   UUID NOT NULL uuid_generate_v4(),
    Image        UUID NOT NULL uuid_generate_v4()
);

CREATE TABLE Passport
(
    Id                   INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    Nationality          VARCHAR(255),
    SocialSecurityNumber VARCHAR(255),
    Image                UUID NOT NULL DEFAULT uuid_generate_v4()
);