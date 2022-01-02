FROM gradle:alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle clean distTar --no-daemon

FROM openjdk:17-slim

LABEL name = "carnival"
LABEL version = "1.0.0"
LABEL maintainer = "abstraq <abstraq@outlook.com>"

WORKDIR /usr/carnival

COPY --from=build /home/gradle/src/build/distributions/carnival-*.tar ./carnival.tar
RUN tar -xvf carnival.tar --strip 1 && rm carnival.tar
CMD ["./bin/carnival"]