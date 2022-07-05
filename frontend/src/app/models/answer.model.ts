import { Question } from "./question.model";

export class Answer {
    id?: string;
    answerText: string;
    question: Question;
}