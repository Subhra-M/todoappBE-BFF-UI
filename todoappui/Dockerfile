FROM nginx:1.17.1-alpine
WORKDIR /app
COPY build/. /usr/share/nginx/html

#install curl
RUN apk add --update curl && rm -rf /var/cache/apk/*


# support running as arbitrary user which belogs to the root group
RUN chmod g+rwx /var/cache/nginx /var/run /var/log/nginx

# comment user directive as master process is run as user in OpenShift anyhow
RUN sed -i.bak 's/^user/#user/' /etc/nginx/nginx.conf

# override the default port 80 on nginx and make the app available on port 8081
RUN sed -i.bak 's/listen\(.*\)80;/listen 3000;/' /etc/nginx/conf.d/default.conf

EXPOSE 3000