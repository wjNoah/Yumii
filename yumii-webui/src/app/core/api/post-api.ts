import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { CreatePostRequest, Post } from './post.model';

@Injectable({ providedIn: 'root' })
export class PostApi {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = '/api/posts';

  findAll(): Observable<Post[]> {
    return this.http.get<Post[]>(this.baseUrl);
  }

  create(request: CreatePostRequest): Observable<Post> {
    return this.http.post<Post>(this.baseUrl, request);
  }
}
