# WorldWalker

This is the back end repository for WorldWalker.

**WorldWalker** is a full-stack vacation planning web application that I developed independently to strengthen my skills in **web development**, **backend architecture**, and **cloud deployment**. It is a personal project, and every aspect of the project—from planning to coding to deployment—was completed by me.

---

## Tech Stack

- **Frontend**: React
- **Frontend Server**: Node.js
- **Backend**: Spring Boot + Spring Security  
- **Database**: MySQL  
- **Deployment**: Deployed to Heroku (frontend + backend)

---

## 🧠 Key Features

- **AI-Powered Trip Planning**  
  Uses the **OpenAI API** to generate customized vacation itineraries based on:
  - Party size
  - Trip duration
  - Age group
  - Travel preferences (e.g., nature, luxury, adventure)

- **User Authentication & Account Management**  
  Built-in user system with:
  - Secure account creation and login
  - Authentication via **Spring Security**
  - Encrypted password storage

- **Itinerary History and Persistence**  
  - Authenticated users can view and retrieve their previously generated plans
  - Plans are stored in a relational MySQL database

- **Separation of Concerns**  
  - Express.js server used as a **middleware layer** between the frontend and external APIs like OpenAI to securely handle API keys and manage CORS

- **Responsive UI**  
  - Simple and intuitive frontend built with React for fast client-side rendering
  - Clean and responsive design optimized for desktop and mobile use

---

## Live Deployment

The application is fully deployed using **Heroku**, with separate build pipelines for the frontend and backend services.  

---

## What I Learned

Through building WorldWalker, I deepened my understanding of:
- Full-stack app architecture
- API proxying and securing API keys
- RESTful API design in Spring Boot
- Database schema design and persistence logic
- User authentication and authorization best practices
- Deployment workflows using Heroku and GitHub
