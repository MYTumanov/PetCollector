CREATE TABLE users(
   id VARCHAR PRIMARY KEY,
   created TIMESTAMP NOT NULL,
   last_update TIMESTAMP NOT NULL,
   rec_version INTEGER NOT NULL,
   deleted BOOLEAN DEFAULT false
);

CREATE TABLE users_tg(
   par_id VARCHAR NOT NULL REFERENCES users(id) UNIQUE,
   telegram_id BIGINT NOT NULL UNIQUE,
   chat_id BIGINT NOT NULL UNIQUE,
   user_name TEXT NOT NULL UNIQUE
);

CREATE DOMAIN phone_number AS TEXT CHECK(VALUE ~ '^[+]*[0-9]{3}[0-9]{3}[0-9]{4,6}$');

CREATE TABLE users_web(
   par_id VARCHAR NOT NULL REFERENCES users(id) UNIQUE,
   last_name VARCHAR,
   first_name VARCHAR,
   mid_name VARCHAR,
   phone_number phone_number UNIQUE,
   login TEXT NOT NULL UNIQUE,
   password TEXT NOT NULL
);

CREATE INDEX idx_users_id ON users(id);

CREATE INDEX idx_userstg_parid ON users_tg(par_id);

CREATE INDEX idx_userstg_chatid ON users_tg(chat_id);

CREATE INDEX idx_userstg_telegramid ON users_tg(telegram_id);

CREATE INDEX idx_usersweb_parid ON users_web(par_id);

CREATE INDEX idx_usersweb_login ON users_web(login);

---------------------------//--------------------------------------

CREATE TABLE debt(
   id VARCHAR PRIMARY KEY,
   created TIMESTAMP NOT NULL,
   last_update TIMESTAMP NOT NULL,
   rec_version INTEGER NOT NULL,
   deleted BOOLEAN DEFAULT false,
   comment TEXT,
   owner_id VARCHAR NOT NULL REFERENCES users(id)
);

CREATE INDEX idx_debt_id ON debt(id);

CREATE INDEX idx_debt_ownerid_deleted_status ON debt(owner_id, deleted);

---------------------------//--------------------------------------

CREATE TABLE debtor(
   id VARCHAR PRIMARY KEY,
   debt_id VARCHAR NOT NULL REFERENCES debt(id),
   created TIMESTAMP NOT NULL,
   last_update TIMESTAMP NOT NULL,
   rec_version INTEGER NOT NULL,
   sum NUMERIC(10, 2),
   status TEXT NOT NULL,
   user_id VARCHAR NOT NULL REFERENCES users(id)
);

CREATE INDEX idx_debtor_id ON debtor(id);

CREATE INDEX idx_debtor_userid_status ON debtor(user_id, status);