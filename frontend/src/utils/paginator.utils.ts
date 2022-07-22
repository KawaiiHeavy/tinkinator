import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
})
export class PaginatorUtils {

    // pageNumber index starts with 0
    getIndexOfPagedObject(pageNumber: number, pageSize: number): number {
        return (pageNumber + 1) * pageSize - 1;
    }

}