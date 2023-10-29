INSERT INTO users VALUES 
    ('1', current_timestamp, current_timestamp, 0, false),
    ('2', current_timestamp, current_timestamp, 0, false),
    ('3', current_timestamp, current_timestamp, 0, false),
    ('4', current_timestamp, current_timestamp, 0, false);

INSERT INTO users_tg VALUES 
    ('1', 123, 321, 'superuser'),
    ('2', 124, 421, 'duperuser'),
    ('3', 125, 521, 'lox');

INSERT INTO users_web VALUES 
    ('1', 'ln_1', 'fn_1', '', '+79254719928', 'login_1', 'pw_1'),
    ('2', 'ln_2', 'fn_2', 'mn_2', '+79254719930', 'login_2', 'pw_2'),
    ('4', 'ln_4', 'fn_4', 'mn_4', '+79254719950', 'login_4', 'pw_4');


INSERT INTO debt VALUES 
    ('1', current_timestamp, current_timestamp, 0, false, 'пицца', '1'),
    ('2', current_timestamp, current_timestamp, 0, false, 'табуретка', '1'),
    ('3', current_timestamp, current_timestamp, 0, false, '!!!!!', '1'),
    ('4', current_timestamp, current_timestamp, 0, false, 'карш', '2'),
    ('5', current_timestamp, current_timestamp, 0, false, 'rfhi2', '2');

INSERT INTO debtor VALUES 
    ('1', '1', current_timestamp, current_timestamp, 0, 200, 'IN_PROCESS', '2'),
    ('2', '1', current_timestamp, current_timestamp, 0, 200, 'IN_PROCESS', '1'),
    ('3', '1', current_timestamp, current_timestamp, 0, 200, 'IN_PROCESS', '3'),
    ('4', '2', current_timestamp, current_timestamp, 0, 125, 'IN_PROCESS', '2'),
    ('5', '2', current_timestamp, current_timestamp, 0, 100, 'IN_PROCESS', '1'),
    ('6', '3', current_timestamp, current_timestamp, 0, 561.12, 'IN_PROCESS', '2'),
    ('7', '3', current_timestamp, current_timestamp, 0, 561.12, 'IN_PROCESS', '1'),
    ('8', '4', current_timestamp, current_timestamp, 0, 330, 'IN_PROCESS', '2'),
    ('9', '4', current_timestamp, current_timestamp, 0, 70, 'IN_PROCESS', '1'),
    ('10', '5', current_timestamp, current_timestamp, 0, 123, 'IN_PROCESS', '2'),
    ('11', '5', current_timestamp, current_timestamp, 0, 321, 'IN_PROCESS', '1');
