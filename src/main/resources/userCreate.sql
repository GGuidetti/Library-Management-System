
INSERT INTO app_user (name, email, password, register_date, phone)
SELECT 'Admin', 'admin@admin.com', '8C6976E5B5410415BDE908BD4DEE15DFB167A9C873FC4BB8A81F6F2AB448A918', CURRENT_TIMESTAMP, '123-456-7890'
    WHERE NOT EXISTS (SELECT 1 FROM app_user WHERE email = 'admin@admin.com');
