insert into usertable (name, pass_word, email_adress)
values
('user', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', 'user@user.user' ),    -- password: password
('admin', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', 'admin@admin.admin');

insert into authorities(username, authority)
values
('user', 'ROLE_USER'),
('admin', 'ROLE_ADMIN');

