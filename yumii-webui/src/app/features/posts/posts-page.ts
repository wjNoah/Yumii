import { Component, inject, signal } from '@angular/core';
import { DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PostApi } from '../../core/api/post-api';
import { Post } from '../../core/api/post.model';

@Component({
  selector: 'app-posts-page',
  imports: [FormsModule, DatePipe],
  templateUrl: './posts-page.html',
  styleUrl: './posts-page.scss',
})
export class PostsPage {
  private readonly api = inject(PostApi);

  protected readonly posts = signal<Post[]>([]);
  protected readonly error = signal<string | null>(null);
  protected title = '';
  protected content = '';

  constructor() {
    this.load();
  }

  protected load(): void {
    this.api.findAll().subscribe({
      next: (posts) => {
        this.posts.set(posts);
        this.error.set(null);
      },
      error: () => this.error.set('Backend not reachable – is Spring Boot running on :8080?'),
    });
  }

  protected create(): void {
    if (!this.title.trim()) {
      return;
    }
    this.api.create({ title: this.title, content: this.content }).subscribe({
      next: (post) => {
        this.posts.update((list) => [...list, post]);
        this.title = '';
        this.content = '';
      },
      error: () => this.error.set('Could not create post.'),
    });
  }
}
