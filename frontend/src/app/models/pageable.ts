export class Pageable<T> {
    content: T[];
    empty: boolean;
    first: boolean;
    last: boolean;
    number: number;
    numberOfElements: number;
    size: number;
    sort: any[];
    totalElements: number;
    totalPages: number;
}