create sequence animal_id_seq as integer;

create table animal (
                               id bigint primary key not null default nextval('animal_id_seq'::regclass),
                               name character varying(255) not null,
                               description text not null,
                               url_image character varying(255),
                               category character varying(255) not null,
                               birth_date timestamp(6) with time zone,
                               status character varying(255) not null,
                               created_at timestamp without time zone default CURRENT_TIMESTAMP,
                               updated_at timestamp(6) with time zone
);

-- Populating Animal table
INSERT INTO animal (name, description, url_image, category, birth_date, status, created_at) VALUES
                                                                                                ('Buddy', 'A friendly golden retriever.', 'http://example.com/images/buddy.jpg', 'DOG', '2020-05-20', 'AVAILABLE', CURRENT_TIMESTAMP),
                                                                                                ('Mittens', 'A calm and affectionate tabby cat.', 'http://example.com/images/mittens.jpg', 'CAT', '2019-07-10', 'AVAILABLE', CURRENT_TIMESTAMP),
                                                                                                ('Charlie', 'A playful Labrador puppy.', 'http://example.com/images/charlie.jpg', 'DOG', '2021-03-15', 'ADOPTED', CURRENT_TIMESTAMP),
                                                                                                ('Luna', 'A curious black and white cat.', 'http://example.com/images/luna.jpg', 'CAT', '2018-11-25', 'ADOPTED', CURRENT_TIMESTAMP);

create sequence adoption_id_seq as integer;

create table adoption (
                                 id bigint primary key not null default nextval('adoption_id_seq'::regclass),
                                 animal_id bigint,
                                 adoption_date timestamp without time zone default CURRENT_TIMESTAMP,
                                 adopter_name character varying(255) not null,
                                 created_at timestamp(6) with time zone not null,
                                 updated_at timestamp(6) with time zone,
                                 foreign key (animal_id) references animal (id)
                                     match simple on update no action on delete no action
);