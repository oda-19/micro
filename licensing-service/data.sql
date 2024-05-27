INSERT INTO public.types (id, name) VALUES
(1, 'Учебная лицензия на 50 компьютеров'),
(2, 'OpenSource'),
(3, 'Бесплатно для некоммерческого использования'),
(4, 'Community - бесплатная лицензия'),
(5, 'Бесплатная лицензия');

INSERT INTO public.licenses (id, namePo, idType, startDate, endDate, count) VALUES
(1, '1C: Предприятие', 1, '2023-09-01', '2024-08-31', 50),
(2, 'Gretl', 2, '2023-09-01', '2024-08-31', 30),
(3, 'VirtualBox', 3, '2023-09-01', '2024-08-31', 35),
(4, 'Wine ', 3, '2023-09-01', '2024-08-31', 35),
(5, 'LibreCAD', 3, '2023-09-01', '2024-08-31', 35),
(6, 'Arduino IDE', 3, '2023-09-01', '2024-08-31', 50),
(7, 'Dbeaver', 4, '2023-09-01', '2024-08-31', 35),
(8, 'Eclipse IDE', 3, '2023-09-01', '2024-08-31', 35),
(9, 'PyCharm ', 4, '2023-09-01', '2024-08-31', 35),
(10, 'VSCodium', 3, '2023-09-01', '2024-08-31', 35),
(11, 'Scilab', 3, '2023-09-01', '2024-08-31', 35),
(12, 'Visual Studio Code', 3, '2023-09-01', '2024-08-31', 35),
(13, 'Android Studio', 3, '2023-09-01', '2024-08-31', 35),
(14, 'WxMaxima', 3, '2023-09-01', '2024-08-31', 35),
(15, 'Anaconda Free', 5, '2023-09-01', '2024-08-31', 35),
(16, 'Libre Office', 3, '2023-09-01', '2024-08-31', 35),
(17, 'Bzip2', 3, '2023-09-01', '2024-08-31', 35),
(18, 'Loginom', 4, '2023-09-01', '2024-08-31', 35),
(19, 'Операционная система Ред ОС 7.3', 5, '2023-09-01', '2024-08-31', 35);