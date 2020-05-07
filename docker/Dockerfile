FROM golang:1.12.9-alpine3.10
LABEL stage=builder
WORKDIR /build
COPY main.go .
RUN go build main.go

FROM alpine:3.10.2
WORKDIR /app
COPY --from=0 build/main .
EXPOSE 8282
CMD ["/app/main"]
