# Запуск
1. Запустите скрипт `run.sh` из папки docker
2. Войдите в Jenkins
3. Войдите в pipeline и нажмите нажмите `собрать` на боковой панели

# Архитектура
Здесь все предельно просто:
1. Исходный код выкачивается из Github из ветки develop
2. Сборка и анализ в Jenkins
3. На основе jar-ника собирается docker-image
4. Push image из шага выше в DockerHub с помощью ТУЗ
5. С небольшой задержкой запускается контейнер с anible, в котором localhost-у назначается роль prod-server. 

Стоит понимать, что из-за этого приходиться прокидывать docker сокеты с localhost, что не является секьюрным решением.

# Credentials
Все необходимые credentials для сборки(ТУЗ для docker и токен для SQ) уже вшиты в конфиги jenkins. При большом желании вы легко сможете их дешифровать :). Ниже будут описаны базовые креды для входа в сервисы:

1. `Jenkins`: admin/d0ff493386554593a7a176c39bd04fb8
2. `Sonar`: admin/password

# P.S.
Не забудьте запустить Docker и ngrok(если хотите узреть работу webhook-а)
