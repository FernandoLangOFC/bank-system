create extension if not exists "uuid-ossp";


CREATE TABLE IF NOT EXISTS "user" (
    "id" uuid NOT NULL DEFAULT uuid_generate_v4(),
    "username" VARCHAR(75) NOT NULL UNIQUE,
    "document_number" VARCHAR(11) NOT NULL,
    "password" TEXT NOT NULL,
    "email" VARCHAR(125) NOT NULL UNIQUE,
    "phone" VARCHAR(11) NOT NULL UNIQUE,
    "created_at" timestamptz NULL DEFAULT CURRENT_TIMESTAMP,
    "updated_at" timestamptz NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT user_document_number_unique UNIQUE ( document_number ),
    CONSTRAINT user_email_unique UNIQUE ( email ),
    CONSTRAINT user_phone_unique UNIQUE ( phone ),
    CONSTRAINT user_id_primary_key PRIMARY KEY ( id )
)