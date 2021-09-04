FROM node:alpine as buildProject
WORKDIR /app
COPY package.json .
RUN npm install
COPY . .
RUN npm run build

FROM nginx
COPY --from=buildProject /app/dist/phone-validator-frontend /usr/share/nginx/html
COPY /nginx.conf /etc/nginx/conf.d/default.conf