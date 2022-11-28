FROM node:16

# Create app directory
WORKDIR /usr/src/petfinder-app

# Install app dependencies
# A wildcard is used to ensure both package.json AND package-lock.json are copied
# where available (npm@5+)
COPY package*.json ./
COPY node_modules ./


# debug mode
RUN apt update -y
RUN apt install nano -y

# Bundle app source
COPY . .

EXPOSE 3000
CMD [ "npm", "start" ]