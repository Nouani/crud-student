import * as Yup from 'yup';
import Student from '../models/Student';

class StudentController {
    async index(req, res) {
        const response = await Student.findAll({
            attributes: ['RA', 'name', 'email'],
        });

        return res.json(response);
    }

    async show(req, res) {
        const { RA } = req.params;

        const response = await Student.findByPk(RA, {
            attributes: ['RA', 'name', 'email'],
        });

        if (!response) {
            return res.status(404).json({ error: 'Estudante não encontrado' });
        }

        return res.json(response);
    }

    async store(req, res) {
        const schema = Yup.object().shape({
            RA: Yup.string().min(5).max(5).required(),
            name: Yup.string().required(),
            email: Yup.string().email().required(),
        });

        if (!(await schema.isValid(req.body))) {
            return res.status(400).json({ error: 'Faltam dados' });
        }

        const studentExists = await Student.findByPk(req.body.RA);
        if (studentExists) {
            return res
                .status(400)
                .json({ error: 'Já existe um estudante com esse RA' });
        }

        const { RA, name, email } = await Student.create(req.body);

        return res.json({
            RA,
            name,
            email,
        });
    }
}

export default new StudentController();
