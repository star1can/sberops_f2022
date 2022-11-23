#!/bin/bash
docker-compose down
docker stop ansible
docker rm ansible
docker stop prod-db
docker rm prod-db
docker stop greeting-service
docker rm greeting-service