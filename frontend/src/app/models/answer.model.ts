import { Question } from "./question.model";
import { Solution } from "./solution.model";

export class Answer {
    id: string;
    answerText: string;
    question: Question;
    solution: Solution;
}