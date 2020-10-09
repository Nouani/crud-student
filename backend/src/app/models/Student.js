import Sequelize, { Model } from 'sequelize';

class Student extends Model {
    static init(sequelize) {
        super.init(
            {
                RA: {
                    type: Sequelize.STRING,
                    allowNull: false,
                    primaryKey: true,
                    autoIncrement: false,
                },
                name: Sequelize.STRING,
                email: Sequelize.STRING,
            },
            {
                sequelize,
            },
        );
    }
}

export default Student;
