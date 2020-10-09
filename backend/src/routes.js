import { Router } from 'express';

import Student from './app/models/Student';

import StudentController from './app/controllers/StudentController';

const routes = new Router();

routes.post('/student', StudentController.store);

export default routes;
