create table user_info (
    user_id          INT    not null AUTO_INCREMENT,
    address          JSON   not null,
    phone_no         JSON   not null,
    PRIMARY KEY (`user_id`)
);