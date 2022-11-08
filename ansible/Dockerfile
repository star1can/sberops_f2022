FROM alpine:3.11
# necessary extensions
RUN apk add --no-cache openssh-client ansible git sshpass
RUN mkdir -p /root/.ssh
# allow connect to all servers
RUN echo "HOST *" > /root/.ssh/config && echo " StrictHostKeyChecking no" >> /root/.ssh/config
# COPY configs
COPY . /ansible
# change workdir
WORKDIR /ansible

CMD [ "" ] 