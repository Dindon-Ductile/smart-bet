import {
  Directive,
  ElementRef,
  HostListener,
  Input
} from '@angular/core';

@Directive({
  selector: '[appNumeric]'
})
export class NumericDirective {
  // tslint:disable-next-line:no-input-rename
  @Input('decimals') decimals = 0;

  private check(value: string): RegExpMatchArray {
    if (this.decimals <= 0) {
      return String(value).match(new RegExp(/^\d+$/));
    } else {
      const regExpString =
        '^\\s*((\\d+(\\.\\d{0,' +
        this.decimals +
        '})?)|((\\d*(\\.\\d{1,' +
        this.decimals +
        '}))))\\s*$';
      return String(value).match(new RegExp(regExpString));
    }
  }

  private run(oldValue): any {
    setTimeout(() => {
      const currentValue: string = this.el.nativeElement.value;
      if (currentValue !== '' && !this.check(currentValue)) {
        this.el.nativeElement.value = oldValue;
      }
    });
  }

  constructor(private el: ElementRef) {}

  @HostListener('keydown', ['$event'])
  onKeyDown(event: KeyboardEvent): any {
    this.run(this.el.nativeElement.value);
  }

  @HostListener('paste', ['$event'])
  onPaste(event: ClipboardEvent): any {
    this.run(this.el.nativeElement.value);
  }
}
