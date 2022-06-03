FROM python:3.10

ENV PORT 8080
ENV HOST 0.0.0.0

EXPOSE 8080

RUN apt-get update -y && \
    apt-get install -y python3-pip

COPY ./requirements.txt /app/requirements.txt

WORKDIR /app

ENTRYPOINT ["python3"]
CMD ["main.py"]