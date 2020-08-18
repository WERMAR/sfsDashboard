# base image
FROM node as build-step
WORKDIR /app

COPY package.json ./
RUN npm install
COPY . .
RUN npm run build

FROM nginx as prod-stage
COPY --from=build-step /app/dist/builderboard /usr/share/nginx/html
CMD ["nginx", "-g", "daemon off;"]

