alter table question modify creator bigint not null;
alter table `comment` modify commentator bigint not null;
DROP INDEX PUBLIC.PRIMARY_KEY_27;
ALTER TABLE USER ALTER COLUMN ID IDENTITY;