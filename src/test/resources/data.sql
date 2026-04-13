INSERT INTO app_user(username, password, role)
VALUES
('user', '$2a$10$1DTvwpXVBArGFixHBuzVJObjTuXhIOkx5pse6KsYs8/C2ckxnGEou', 'USER'),
('admin', '$2a$10$cDZgyF4xaPMmmoRW3OVcmuf.8o2YSx8.M7CeRKqi.1PVw.t3E8uEC', 'ADMIN');

INSERT INTO recipe (title, instructions, app_user_id)
VALUES
('Kanakeitto', 'Keitä keitto ja mausta se mausteilla.', (SELECT id FROM app_user WHERE username='admin')
),
('Lohisalaatti', 'Leikkaa kasvikset ja sekoita lohen sekaan', (SELECT id FROM app_user WHERE username='admin')
);