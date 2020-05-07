FROM ubuntu:18.04

RUN apt-get update
RUN apt install -y software-properties-common
RUN apt-add-repository --yes --update ppa:ansible/ansible
RUN apt-get install -y ansible

WORKDIR tmp
COPY ansible/ .
EXPOSE 8282

CMD ["ansible-playbook", "playbook.yml"]