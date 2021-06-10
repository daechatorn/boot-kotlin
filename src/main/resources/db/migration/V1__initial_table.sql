create table application_info (
    application_id          VARCHAR(100)    not null,
    thai_id   	            VARCHAR(20)     not null,
    dob  	                VARCHAR(10),
    email                   VARCHAR(100),
    email_verified          BOOLEAN,
    created_datetime 	    TIMESTAMP       not null
);